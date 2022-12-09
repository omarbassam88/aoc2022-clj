(ns aoc2022.day08-test
  (:require [aoc2022.day08 :refer [parse-input part-1 part-2]]
            [clojure.test :as t :refer [deftest testing is]]
            [clojure.java.io :as io]))

(def input (slurp (io/resource "day08.test.txt")))

(deftest day08-part1
  (testing "Day 8 Part 1"
    (is (= (part-1 (parse-input input)) 21))))

(deftest day08-part2
  (testing "Day 8 Part 2"
    (is (= (part-2 (parse-input input)) 8))))
