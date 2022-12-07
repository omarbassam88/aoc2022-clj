(ns aoc2022.day07-test
  (:require [aoc2022.day07 :refer [parse-input part-1 part-2]]
            [clojure.test :as t :refer [deftest testing is]]
            [clojure.java.io :as io]))

(def input (slurp (io/resource "day07.test.txt")))

(deftest day07-part1
  (testing "Day 6 Part 1"
    (is (= (part-1 (parse-input input)) 95437))))

(deftest day07-part2
  (testing "Day 6 Part 2"
    (is (= (part-2 (parse-input input)) 24933642))))
