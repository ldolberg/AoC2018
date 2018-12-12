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



; (defn cmp[visited x y]
;         (if (< (count (filter (partial can-visit? visited) (dep x))) (count (filter (partial can-visit? visited) (dep y))))
;            (< (int (first x)) (int (first y))))
;            (= (count (filter (partial can-visit? visited) (dep x))) (count (filter (partial can-visit? visited) (dep y)))))


(println (apply concat (loop [ pending no-dep-nodes
        visited (list )
        res [] ]
    
    (let [ [ h & t ] pending
           neigbrs  (adj h)
           next_steps (sort (filter (partial can-visit? (cons h visited))  neigbrs))
           wsteps (sort-by second (concat (map #(list %1 (int (first %1))) t) (map #(list %1 (+ (- (int (first %1)) 4) (int (first h)))) next_steps)))
           ; TODO pasar esto como parametro
           ]
         (println (or  (nil? h)  (list h (- (int (first h)) 4) wsteps)))
        (if (nil? h)
            res
            (recur (concat t next_steps) (conj visited h) (conj res h))) 
    )
)))

; [BTVYIWRSKMAQGXUZHPOCDLJNFE]
;  BTVYIWRSKMAGQZUXHPOCDLJNFE
