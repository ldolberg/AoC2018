(defn node [x y]
    {:left nil :right nil :key x :val y})
  
(defn search [bt key]
    (if (not= nil bt)
        (if (= (:key bt) key)
            (:val bt)
            (if (> (:key bt) key)
                (search (:right bt) key)
                (search (:left bt) key)
            )
        )
        -1
    )
)

(defn add [bt key] 
    (insert bt key 1)
)

(defn insert [bt key val]
    (if (not= bt nil)
        (if (> key (:key bt))
            {:left (:left bt) :key (:key bt) :val (:val bt) :right (insert (:right bt) key val)}
            {:left (insert (:left bt) key val) :key (:key bt) :val (:val bt) :right (:right bt)}
        )
        (node key val)
    )
)

(defn size [f]
    (if (nil? f)
      0
      (+ 1 (+ (size (:left f)) (size (:right f))))
    )
)


(defn increment [bt key]
    (if (not= bt nil)
        (if (= (:key bt) key) 
            {:left (:left bt) :key key :val (inc (:val bt)) :right (:right bt) }
            (if (> key (:key bt))
                {:left (:left bt) :key (:key bt) :val (:val bt) :right (increment (:right bt) key)}
                {:left (increment (:left bt) key) :key (:key bt) :val (:val bt) :right (:right bt)}
            )
        )    
    )
)

(defn in-order [bt]
    ; (println bt)
    (if (nil? bt)
        nil
        (concat (in-order (:left bt)) (list {:key (:key bt) :val (:val bt)}) (in-order (:right bt)))   
    )
)