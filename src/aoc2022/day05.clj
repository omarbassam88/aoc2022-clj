(ns aoc2022.day05
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(def input (slurp (io/resource "day05.txt")))

(defn parse-row [s]
  (as-> s $
    (str/replace $ #"\s{4}" " * ")
    (str/trim $)
    (str/split $ #"\s+")))

(defn parse-stacks [strs]
  (let [rows (drop-last (map parse-row strs))
        cols (apply mapv vector rows)]
    (mapv (fn [col] (remove #(= % "*") col)) cols)))

(defn parse-moves [strs]
  (mapv (fn [s] (remove nil? (mapv parse-long (str/split s #"\s"))))
        strs))

(defn parse-input [input]
  (let [[stack-strs moves-str] (->> (str/split input #"\n\n")
                                    (map str/split-lines))]
    (vector (parse-stacks stack-strs) (parse-moves moves-str))))

(defn make-move [stacks n from to]
  (let [from-stack (stacks (dec from))
        to-stack (stacks (dec to))]
    (assoc stacks
           (dec from) (drop n from-stack)
           (dec to) (apply conj to-stack (take n from-stack)))))

(defn make-move-multiple [stacks n from to]
  (let [from-stack (stacks (dec from))
        to-stack (stacks (dec to))]
    (assoc stacks
           (dec from) (drop n from-stack)
           (dec to) (apply conj to-stack (reverse (take n from-stack))))))

(defn top-crates [stacks]
  (->> stacks
         (map first)
         (map #(first (re-seq #"\w" %)))
         (apply str)))

(defn part-1 [[stacks moves]]
  (if (empty? moves)
    (top-crates stacks)
    (let [[n from to] (first moves)]
      (-> (make-move stacks n from to)
          (vector (rest moves))
          part-1))))

(defn part-2 [[stacks moves]]
  (if (empty? moves)
    (top-crates stacks)
    (let [[n from to] (first moves)]
      (-> (make-move-multiple stacks n from to)
          (vector (rest moves))
          part-2))))

(defn -main []
  (let [data (parse-input input)]
    (println  "Solution to Part 1 is: " (part-1 data))
    (println  "Solution to Part 2 is: " (part-2 data))))
