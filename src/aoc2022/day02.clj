(ns aoc2022.day02 
  (:require
    [clojure.java.io :as io]
    [clojure.string :as str]))

(def input (slurp (io/resource "day02.txt")))

(defn parse-input [input]
  (->> input
       str/split-lines
       (map #(str/split % #" "))))


(defn part-1 [data]
  ;;Rock   A X 1
  ;;Paper  B Y 2
  ;;Sciss  C Z 3
  (let [scores {"A" {"X" 4 "Y" 8 "Z" 3}
                "B" {"X" 1 "Y" 5 "Z" 9}
                "C" {"X" 7 "Y" 2 "Z" 6}}]
       (->> (map #(get-in scores %) data)
            (reduce + 0))))

(defn part-2 [data]
  ;; A Rock  1 | X Lose
  ;; B Paper 2 | Y Draw
  ;; C Sciss 3 | Z Win
  (let [scores {"A" {"X" 3 "Y" 4 "Z" 8}
                "B" {"X" 1 "Y" 5 "Z" 9}
                "C" {"X" 2 "Y" 6 "Z" 7}}]
       (->> (map #(get-in scores %) data)
            (reduce + 0))))
 
(defn -main []
  (let [data (parse-input input)]
    (println "Solution to Part 1 is: " (part-1 data))
    (println "Solution to Part 2 is: " (part-2 data))))
