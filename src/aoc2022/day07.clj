(ns aoc2022.day07 
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(def input (slurp (io/resource "day07.txt")))

(defn run-command [command dirs path]
  (when (= "cd" (first command))
    (if (= ".." (second command))
      (swap! path pop)
      (swap! path conj (second command)))))

(defn parse-input [input]
  (let [dirs (atom {})
        path (atom [])]
    (doseq [line (str/split-lines input)]
      (if (str/starts-with? line "$")
        (run-command (rest (str/split line #"\s")) dirs path)
        (let [file (str/split line #"\s")]
          (when-not (= (first file) "dir")
            (swap! dirs assoc-in (conj @path (second file))
                   (parse-long (first file)))))))
    @dirs))

(defn dir-size [dir]
  (reduce (fn [acc [k v]]
            (if (map? v)
              (+ acc (dir-size v))
              (+ acc v)))
          0 dir))

(defn walk-dirs
  ([dir] (walk-dirs dir (atom {})))
  ([dir memo]
   (doseq [[k v] dir :when (map? v)]
     (swap! memo assoc k (dir-size v))
     (walk-dirs v memo))
   @memo))

(defn part-1
  ([data] (part-1 data ["/"]))
  ([data path]
   (if-not (map? (get-in data path))
     0
     (reduce + 0
             (for [[k v] (get-in data path)
                   :when (map? v)]
               (+ (if (< (dir-size v) 100000) (dir-size v) 0)
                  (part-1 data (into path [k]))))))))

(defn part-2 [data]
  (let [used (dir-size data)
        needed (- used 40000000)]
    (first (sort (filter (fn [s] (> s needed)) 
                         (vals (walk-dirs data)))))))

(defn -main []
  (let [data (parse-input input)]
    (println  "Solution to Part 1 is: " (part-1 data))
    (println  "Solution to Part 2 is: " (part-2 data))))
