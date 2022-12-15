(ns aoc2022.day11-test
  (:require [aoc2022.day11 :refer [parse-input part-1 part-2]]
            [clojure.test :as t :refer [deftest testing is]]
            [clojure.java.io :as io]))

(def input (slurp (io/resource "day11.test.txt")))

(deftest day11-part1
  (testing "Day 11 Part 1"
    (is (= (part-1 (parse-input input)) 10605))))

(deftest day11-part2
  (testing "Day 11 Part 1"
    (is (= (part-2 (parse-input input)) 2713310158))))
