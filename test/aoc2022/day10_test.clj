(ns aoc2022.day10-test
  (:require [aoc2022.day10 :refer [parse-input part-1 part-2]]
            [clojure.test :as t :refer [deftest testing is]]
            [clojure.java.io :as io]))

(def input (slurp (io/resource "day10.test.txt")))

(deftest day10-part1
  (testing "Day 10 Part 1"
    (is (= (part-1 (parse-input input)) 13140))))
