(ns aoc2022.day10 
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(def input (slurp (io/resource "day10.txt")))

(defn parse-input [input]
  (for [entry (str/split-lines input)]
    (if (str/starts-with? entry "addx")
      (let [v  (parse-long (second (str/split entry #" ")))]
        v))))

(defn x-register-vals [data]
  (loop [[signal & signals] data 
         X 1
         res [1]]
    (if (not signals)
      res
      (if (not signal)
        (recur signals X (conj res X))
        (let [newX (+ signal X)]
          (recur signals newX (conj res newX newX)))))))
  
(nth (x-register-vals (parse-input input)) 218)

(defn part-1 [data]
  (reduce +
          (for [i [20 60 100 140 180 220]]
            (* i (nth (x-register-vals data) (- i 2))))))

(defn screen-pixel [crt-pos sprite-pos]
  (case (- crt-pos sprite-pos)
    (-1 0 +1) "🬎"
    " "))

(defn part-2 [data]
  (->> (x-register-vals data)
       (concat [1])
       (map screen-pixel (cycle (range 0 40)))
       (partition 40)
       (map str/join)
       (str/join "\n")))

(part-2 (parse-input input))

(defn -main []
  (let [data (parse-input input)]
    (println  "Solution to Part 1 is:" (part-1 data))
    (println  "Solution to Part 2 is:")
    (println (part-2 data))))
