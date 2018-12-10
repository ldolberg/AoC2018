(import java.lang.Integer)
(def fp "day 6/input")

(def input (map #(map bigint (rest (re-find #"(\d+), (\d+)" %  ))) (clojure.string/split (slurp fp) #"\n")))

(def not-nil? (complement nil?))

(def a (first (apply min-key first input)))
(def b (second (apply min-key second input)))
(def c (first (apply max-key first input)))
(def d (second (apply max-key second input)))
(defn is-borderline [x] (let [[x0 x1] x] (or (<= x0 a) (>= x0 c) (<= x1 b) (>= x1 d))))
(def box [[a b] [a d] [c b] [c d]])

(defn abs [x]
    (if (< x 0)
        (* -1 x)
        x
    )    
)

(defn distance [x y]
    (let [[x0 x1] x
          [y0 y1] y]
    (+ (abs (- y0 x0)) (abs (- y1 x1)))
    )
)

(def edge-coordinates
    (reduce concat (list  (map #(list a %1) (range d))
     (map #(list c %1) (range d))
     (map #(list b %1) (range c))
     (map #(list b %1) (range c)))))

(defn get-region [x]
 (let [distances (sort (map (partial distance x) input))]
    (if (not= (first distances) (second distances))
        (apply min-key (partial distance x) input)
        (list -1 -1)
    )
  )
)


(def pinput (filter #((complement some) #{%} (map get-region edge-coordinates)) input))


(def grid (reduce concat (map (fn [i] (map (fn [j] [i j]) (range b (inc d)))) (range a (inc c)))))
(def closest (map get-region grid))
; (println (map #(filter (complement is-borderline) %1) closest))

(def regions (sort-by val (frequencies closest)))

; (println (count (map println (sort-by second (filter #(some #{(first %1)} pinput) regions)))))


(defn in-region [k x]
    (< (reduce 
        + 
        (loop [[h & t] input
           res (list)]
            (if h
                (recur t (conj res (distance x h)))
                res
            )))
     k)    
)

(println (count (filter (partial in-region 10000) grid)))