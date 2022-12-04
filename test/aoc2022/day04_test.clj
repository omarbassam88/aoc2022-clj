(ns aoc2022.day04-test
  (:require [aoc2022.day04 :refer [part-1 part-2 parse-input]]
            [clojure.test :as t :refer [deftest testing is]]
            [clojure.java.io :as io]))

(def sample (parse-input (slurp (io/resource "day04.test.txt"))))

(deftest day04-part1
  (testing "Day 04 Part 1 "
    (is (= (part-1 sample) 2))))

(deftest day04-part2
  (testing "Day 04 Part 2 "
    (is (= (part-2 sample) 4))))
