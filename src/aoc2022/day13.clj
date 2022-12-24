(ns aoc2022.day13 
  (:require
   [clojure.edn :as edn]
   [clojure.java.io :as io]
   [clojure.string :as str]))

(def input (slurp (io/resource "day13.txt")))

(defn parse-input [input]
  (->> (str/split input #"\n\n")
       (mapv str/split-lines)
       (mapv #(mapv edn/read-string %))))

(defn compare-packets [l r]
  (cond
    (and (number? l) (number? r))
    (compare l r)
    (number? l) (compare-packets [l] r)
    (number? r) (compare-packets l [r])
    :else
    (or (->> (mapv compare-packets l r)
             (drop-while zero?)
             first)
        (- (count l) (count r)))))

(defn part-1 [data]
  (->> data 
       (keep-indexed (fn [i [l r]]
                       (when (neg? (compare-packets l r))
                         (inc i))))
       (reduce + 0)))

(defn part-2 [data]
  (->> data
       (reduce (fn [acc [l r]]
                  (conj acc l r)) [])
       (#(conj % [[2]] [[6]]))
       (sort compare-packets)
       vec
       (keep-indexed
          (fn [i p]
            (when (or (= p [[2]])
                      (= p [[6]]))
              (inc i))))
       (apply *)))

(defn -main []
  (println  "Solution to Part 1 is: " (part-1 (parse-input input)))
  (println  "Solution to Part 2 is: " (part-2 (parse-input input))))
