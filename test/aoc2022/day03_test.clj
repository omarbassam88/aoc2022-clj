(ns aoc2022.day03-test
  (:require [aoc2022.day03 :refer [part-1 part-2 parse-input]]
            [clojure.test :as t :refer [deftest testing is]]
            [clojure.java.io :as io]))

(def sample (parse-input (slurp (io/resource "day03.test.txt"))))

(deftest day03-part1
  (testing "Day 03 Part 1 "
    (is (= (part-1 sample) 157))))

(deftest day03-part2
  (testing "Day 03 Part 2 "
    (is (= (part-2 sample) 70))))
