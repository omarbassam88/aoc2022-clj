(ns aoc2022.day12-test
  (:require [aoc2022.day12 :refer [parse-input part-1 part-2]]
            [clojure.test :as t :refer [deftest testing is]]
            [clojure.java.io :as io]))

(def input (slurp (io/resource "day12.test.txt")))

(deftest day12-part1
  (testing "Day 12 Part 1"
    (is (= (part-1 (parse-input input)) 31))))

(deftest day12-part2
  (testing "Day 12 Part 1"
    (is (= (part-2 (parse-input input)) 29))))
