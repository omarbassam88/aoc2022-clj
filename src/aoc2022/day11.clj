(ns aoc2022.day11 
  (:require
   [clojure.edn :as edn]
   [clojure.java.io :as io]
   [clojure.string :as str]))

(def input (slurp (io/resource "day11.txt")))

(defn parse-operation [op-str]
  (let [[_ op n] (str/split op-str #" ")]
    (fn [old]
      ((case op "*" * "+" +)
       old (if (= n "old") old (edn/read-string n))))))

(defn parse-monkey [monkey-str]
  (let [[index items op divisor success fail] (str/split-lines monkey-str)]
    {:items (map edn/read-string (re-seq #"\d+" items))
     :inspected 0
     :op  (parse-operation (second (str/split op #"= ")))
     :divisor (edn/read-string (first (re-seq #"\d+" divisor)))
     :success (edn/read-string (first (re-seq #"\d+" success)))
     :fail (edn/read-string (first (re-seq #"\d+" fail)))}))

(defn parse-input [input]
  (->> (str/split input #"\n\n")
       (mapv parse-monkey)))

(defn monkeys-round [monkeys]
  (vals
   (reduce
    (fn [acc [i m]]
      (let [items (map #(quot % 3)
                       (map (:op m) (:items (get acc i))))
            divisor (:divisor m)
            success-items (filter #(zero? (rem % divisor)) items)
            failed-items (filter #(not= (rem % divisor) 0) items)]
        (-> (assoc-in acc [i :items] '())
            (update-in  [i :inspected]  + (if (empty? items) 0 (count items)))
            (update-in [(:success m) :items] concat success-items)
            (update-in [(:fail m) :items] concat failed-items))))
    (zipmap (range (count monkeys)) monkeys)
    (zipmap (range (count monkeys)) monkeys))))

(defn monkeys-round-2 [monkeys]
  (vals
   (reduce
    (fn [acc [i m]]
      (let [items (map #(mod % (* 2 3 5 7 11 13 17 19 23))
                       (map (:op m) (:items (get acc i))))
            divisor (:divisor m)
            success-items (filter #(zero? (rem % divisor)) items)
            failed-items (filter #(not= (rem % divisor) 0) items)]
        (-> (assoc-in acc [i :items] '())
            (update-in  [i :inspected]  + (if (empty? items) 0 (count items)))
            (update-in [(:success m) :items] concat success-items)
            (update-in [(:fail m) :items] concat failed-items))))
    (zipmap (range (count monkeys)) monkeys)
    (zipmap (range (count monkeys)) monkeys))))

(defn total-rounds [monkeys n round-fn]
  (loop [monkeys monkeys 
         round 0
         res (map :inspected monkeys)]
    (if (>= round n)
      res
      (let [monkeys (round-fn monkeys)
            res (map :inspected monkeys)]
        (recur monkeys (inc round) res)))))
        

(defn part-1 [data]
  (->> (total-rounds data 20 monkeys-round)
       (sort >)
       (take 2)
       (apply *)))

(defn part-2 [data]
  (->> (total-rounds data 10000 monkeys-round-2)
       (sort >)
       (take 2)
       (apply *)))


(defn -main []
  (let [data (parse-input input)]
    (println  "Solution to Part 1 is: " (part-1 data))
    (println  "Solution to Part 2 is: " (part-2 data))))

