(ns aoc2022.day01
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(def input (slurp (io/resource "day01.txt")))

(defn parse-entry [entry]
  (->> entry
       str/split-lines
       (mapv parse-long)
       (reduce + 0)))

(defn parse-input [input]
  (->> input
       (#(str/split % #"\n\n"))
       (mapv parse-entry)))

(defn part-1 [data]
  (apply max data))

(defn part-2 [data]
  (->> data
       (map int)
       (sort >)
       (take 3)
       (apply +)))

(defn -main [& args]
  (println "AOC Day 01")
  (println "Answer to part 1 is " (part-1 (parse-input input)))
  (println "Answer to part 2 is " (part-2 (parse-input input))))
