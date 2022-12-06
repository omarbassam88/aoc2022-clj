(ns aoc2022.day06
  (:require
   [clojure.java.io :as io]))

(def input (slurp (io/resource "day06.txt")))

(defn find-marker [data marker-size]
  (let [packs (partition marker-size 1 data)]
    (-> (map #(apply distinct? %) packs)
        (.indexOf true)
        (+ marker-size))))

(defn part-1 [data]
  (find-marker data 4))
        
(defn part-2 [data]
  (find-marker data 14))
        
(defn -main []
  (println  "Solution to Part 1 is: " (part-1 input))
  (println  "Solution to Part 2 is: " (part-2 input)))
