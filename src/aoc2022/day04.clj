 (ns aoc2022.day04 
   (:require
    [clojure.java.io :as io]
    [clojure.string :as str]))

(def input (slurp (io/resource "day04.txt")))

(defn parse-input [input]
 (->> input
      str/split-lines
      (mapv #(re-seq #"\d+" %))
      (mapv #(mapv parse-long %))))

(defn fully-contains? [[s1 e1 s2 e2]]
  (or (<= s1 s2 e2 e1)
      (<= s2 s1 e1 e2)))

(defn overlaps? [[s1 e1 s2 e2]]
  (or (fully-contains? [s1 e1 s2 e2])
      (<= s2 e1 e2)
      (<= s1 e2 e1)))

(defn part-1 [data]
  (count (filterv fully-contains? data)))

(defn part-2 [data]
  (count (filterv overlaps? data)))

(defn -main []
  (let [data (parse-input input)]
    (println  "Solution to Part 1 is: " (part-1 data))
    (println  "Solution to Part 2 is: " (part-2 data))))
