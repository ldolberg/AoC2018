(def fp "day 6/input")

(def input (map #(rest (re-find #"Step (\W).*(\W)" % )) (clojure.string/split (slurp fp) #"\n")))
