(load "bst")

(def t (node 25 1))

(def r (node 0 1))

(def tree (add (add (add (add (add (node 0 1) 5) -1) 25) -10) 15))

(println (in-order tree))