(ns aoc2022.day05-test
  (:require [aoc2022.day05 :refer [part-1 part-2 parse-input]]
            [clojure.test :as t :refer [deftest testing is]]
            [clojure.java.io :as io]))

(def sample (parse-input (slurp (io/resource "day05.test.txt"))))

(deftest day05-part1
  (testing "Day 05 Part 1 "
    (is (= (part-1 sample) "CMZ"))))

(deftest day05-part2
  (testing "Day 05 Part 2 "
    (is (= (part-2 sample) "MCD"))))
