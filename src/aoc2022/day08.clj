(ns aoc2022.day08
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(def input (slurp (io/resource "day08.txt")))

(defn parse-input [input]
 (->> input
      str/split-lines
      (mapv #(str/split % #""))
      (mapv #(mapv parse-long %))))

(defn get-directions [m row col]
  (list
   ;; Left
   (reverse (take col (get m row)))
   ;; Right
   (drop (inc col) (get m row))
   ;; Top
   (reverse (take row (map #(nth % col) m)))
   ;; Down
   (drop (inc row) (map #(nth % col) m))))

(defn edge? [m row col]
  (or (= row 0) (= row (dec (count m)))
      (= col 0) (= col (dec (count (first m))))))

(defn visible? [m row col]
  (let [directions (get-directions m row col)]
    (if (edge? m row col)
      true
      (some true?
            (for [direction directions]
              ;; Left
              (every? #(< % (get-in m [row col])) direction))))))

(defn part-1 [data]
  (count
   (filter true?
           (flatten
            (for [row (range (count data))]
              (map #(visible? data row %)
                   (range (count (first data)))))))))

(defn scenic-score [m row col]
  (let [directions (get-directions m row col)
        tree (get-in m [row col])]
    (apply *
      (for [direction directions]
        (count
         (reduce (fn [acc t]
                   (if (and (not-empty acc) (>= (last acc) tree))
                      acc
                      (conj acc t)))
          [] direction))))))
                
(defn part-2 [data]
  (apply max
    (flatten
     (for [row (range (count data))]
       (map #(scenic-score data row %)
            (range (count (first data))))))))

(defn -main []
  (let [data (parse-input input)]
    (println  "Solution to Part 1 is: " (part-1 data))
    (println  "Solution to Part 2 is: " (part-2 data))))
