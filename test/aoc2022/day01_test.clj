(ns aoc2022.day01-test
  (:require [aoc2022.day01 :refer [part-1 part-2 parse-input]]
            [clojure.test :as t :refer [deftest testing is]]
            [clojure.java.io :as io]))


(def sample (parse-input (slurp (io/resource "day01.test.txt"))))

(deftest day01-part1
  (testing "Day 01 Part 1 "
    (is (= (part-1 sample) 24000))))

(deftest day01-part2
  (testing "Day 01 Part 2"
    (is (= (part-2 sample) 45000))))
