(ns aoc2022.day03 
  (:require
   [clojure.java.io :as io]
   [clojure.set :as set]
   [clojure.string :as str]))

(def input (slurp (io/resource "day03.txt")))

(defn parse-input [input]
  (->> input
       str/split-lines))

(defn find-common-char [strings]
  (->> strings
       (map set)
       (apply set/intersection)
       first))

(defn priority [c]
  (- (int c)
     (if (<= (int \a) (int c) (int \z)) 96 38)))

(defn score [entries]
  (->> entries
       (mapv find-common-char)
       (mapv priority)
       (reduce + 0)))

(defn part-1 [data]
  (->> data
       (mapv #(partition (/ (count %) 2) %))
       score))

(defn part-2 [data]
  (->> data
       (partition 3)
       score))

(defn -main []
  (let [data (parse-input input)]
    (println  "Solution to Part 1 is: " (part-1 data))
    (println  "Solution to Part 2 is: " (part-2 data))))
