(def fp "day 7/input")

(def input (map #(rest (re-find #"Step\s([A-Z]).*([A-Z])" % )) (clojure.string/split (slurp fp) #"\n")))

(def dependencies (map #(list (first %1) (map first (second %1))) (group-by second input)))
(def adj-matrix (group-by first input))
(def no-dep-nodes (sort (filter (fn[v] (not (some #(= v %1) (map first dependencies)))) (map first adj-matrix))))

(defn adj [x]
    (map second (get adj-matrix x))    
)
(defn dep [x]
    (rest (flatten (filter #(= (first %) x ) dependencies)))
)

(defn can-visit? [visited x]  (every? (fn[r] (some #(= % r) visited)) (dep x)))

(defn ord[x]
  (- (+ 61 (int (first x))) (int \A)))


(defn schedule [func] 
  (apply concat (loop [ pending (partition 2 (interleave no-dep-nodes (map ord no-dep-nodes)))
          visited (list )
          res [] ]
      (let [ [ f & t ] pending
            [h c & r] f
            neigbrs  (adj h)
            next_steps (filter (partial can-visit? (cons h visited))  neigbrs)
            ]
          (if (nil? f)
              res
              (recur (sort-by func (concat t (map #(list %1 (+ c (ord %1))) next_steps)))
                    (conj visited h) 
                    (conj res f)))))))

(println (map first (partition 2 (schedule first)))) ;; Lexicographic-order
(println (first (reverse (schedule second)))) ;; Task-duration
