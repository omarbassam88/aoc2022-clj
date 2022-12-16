(ns aoc2022.day12
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(def input (slurp (io/resource "day12.txt")))

(defn parse-input [input]
  (let [terrain (str/replace input #"\n *" "~")
        width (inc (str/index-of terrain "~"))
        terrain (str (str/join (repeat width "~"))
                     terrain
                     (str/join (repeat width "~")))
        start (str/index-of terrain \S)
        end (str/index-of terrain \E)
        terrain (-> terrain
                    (str/replace \S \a)
                    (str/replace \E \z)
                    (. getBytes))]
    {:terrain terrain :width width :start start :end end}))

(defn part-1 [data]
  (let [{:keys [terrain width start end]} data
        directions [-1 +1 (- width) (+ width)] ;; left right up down
        VISITED (byte \~)]
    (loop [hikers [[start (byte \a) 0]]]
      (let [hikers (for [[position height distance] hikers

                         :when (if (not= (aget terrain position) VISITED)
                                   (aset terrain position VISITED))
                         :let [max-height (inc height)
                               distance (inc distance)]

                         direction directions

                         :let [neighbour (+ position direction)
                               height (aget terrain neighbour)]
                         :when (<= height max-height)]
                     [neighbour height distance])

            [winner] (filterv
                      (fn [[position height distance]] (= position end))
                      hikers)]
        (if winner
          (nth winner 2)
          (recur hikers))))))

(defn part-2 [data]
  (let [{:keys [terrain width start end]} data
        directions [-1 +1 (- width) (+ width)] ;; left right up down
        VISITED (byte \~)]
    (loop [hikers [[end (byte \z) 0]]]
      (let [hikers (for [[position height distance] hikers

                         :when (if (not= (aget terrain position) VISITED)
                                   (aset terrain position VISITED))

                         :let [min-height (dec height)
                               distance (inc distance)]

                         direction directions

                         :let [neighbour (+ position direction)
                               height (aget terrain neighbour)]
                         :when (>= height min-height)]

                     [neighbour height distance])

            [winner] (filterv
                      (fn [[position height distance]] (= height (byte \a)))
                      hikers)]
        (if winner
          (nth winner 2)
          (recur hikers))))))

(defn -main []
  (println  "Solution to Part 1 is: " (part-1 (parse-input input)))
  (println  "Solution to Part 2 is: " (part-2 (parse-input input))))
