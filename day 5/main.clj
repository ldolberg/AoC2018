(def input (slurp "day 5/input"))
(def not-nil? (complement nil?))

(defn match [x y]
    ; (println x y (or (= (clojure.string/upper-case y) (str x)) (= (clojure.string/upper-case x) (str y))))
    (if (and (not= x y) (not-nil? x) (not-nil? y)) 
        (or (= (clojure.string/upper-case y) (str x)) (= (clojure.string/upper-case x) (str y)))
        false
    )
)
(defn react [x]
    (loop [[f s & r] x
           res []]
        (if (not-nil? s)
            (if (match f s)
                (recur r res)
                (recur (concat (list s) r) (conj res f))
            )
            (conj res f)
        )))


(defn reduce-chain [input] (loop [x input]
    (let [xr (react x)]
    (if (= (count x) (count xr))
        x
        (recur xr))    
)))

(def ans (reduce-chain input))

(println (count ans))
; (defn rem [s x]
;     (clojure.string/replace s x "")
; )
(defn woc [c input]
    (filter #(and (not= (char (+ 65 c) ) %1) (not= (char (+ 97 c)) %1)) input)
)

(println (loop [chrs (range 26) res []]
    (let [ [h & t] chrs 
        woinput (woc h ans)]
        (if (nil? h)
            res
            (if (> (- (count ans) (count woinput)) 0)
                (recur t (conj res (count (reduce-chain woinput))))
                (recur t res)
            )
        )
    )
))

; (println (count (react (filter #(not= (clojure.string/lower-case %1) "b") ans))))
; (println (apply min-key (fn [i] (count (react (filter #(not= (int (clojure.string/lower-case %1)) i) ans)))) (range 97 123)))
