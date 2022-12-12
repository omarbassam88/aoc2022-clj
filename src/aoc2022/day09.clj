(ns aoc2022.day09
  (:require
   [clojure.java.io :as io]))

(def input (slurp (io/resource "day09.txt")))

(defn parse-input [input]
  (for [[_ lrud n] (re-seq #"(L|R|U|D) (\d+)" input)
        direction (repeat (parse-long n) ({\L [-1 0]
                                           \R [+1 0]
                                           \U [0 -1]
                                           \D [0 1]} (nth lrud 0)))]
    direction))

(defn plus [[x y] [dx dy]]
  [(+ x dx) (+ y dy)])

(defn minus [[x y] [dx dy]]
  [(- x dx) (- y dy)])

(def forces1
  {[-2 0] [-1 0]
   [+2 0] [+1 0]
   [0 -2] [0 -1]
   [0 +2] [0 +1]

   [-2 -1] [-1 -1]
   [-2 +1] [-1 +1]
   [+2 -1] [+1 -1]
   [+2 +1] [+1 +1]
   [-1 -2] [-1 -1]
   [+1 -2] [+1 -1]
   [-1 +2] [-1 +1]
   [+1 +2] [+1 +1]})

(defn part-1 [data]
  (loop [head [0 0]
         tail head
         visited #{}
         [direction & directions] data]
    (let [visited (conj visited tail)]
      (if (not direction)
        (count visited)
        (let [head (plus head direction)
              delta (minus head tail)
              force (forces1 delta)
              tail (if force (plus tail force) tail)]
          (recur head tail visited directions))))))

(def forces2
  (assoc forces1
         [-2 -2] [-1 -1]
         [-2 +2] [-1 +1]
         [+2 -2] [+1 -1]
         [+2 +2] [+1 +1]))

(defn follow [[moved-head first-tail & more-tails :as rope]]
  (if (not first-tail)
    rope
    (let [delta (minus moved-head first-tail)
          force (forces2 delta)]
      (if (not force)
        rope
        (cons moved-head (follow
                          (cons (plus first-tail force) more-tails)))))))

(defn part-2 [data]
  (loop [[head & tails] (repeat 10 [0 0])
         visited #{}
         [direction & directions] data]
    (let [visited (conj visited (last tails))]
      (if (not direction)
        (count visited)
        (let [head (plus head direction)]
          (recur (follow (cons head tails)) visited directions))))))

(defn -main []
  (let [data (parse-input input)]
    (println  "Solution to Part 1 is: " (part-1 data))
    (println  "Solution to Part 2 is: " (part-2 data))))
