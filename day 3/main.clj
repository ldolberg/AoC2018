(defn count-rep [l e]
  (count (filter #(= % e) l)))

(def fp "day 3/input")          

(defn numbers-do [f]
  (let [numbers (clojure.string/split (clojure.string/replace (slurp fp) #"\+" "") #"\n")] (f numbers)))


(defn load-order [x] 
  (let [order (rest (re-find #"#(\d+) @ (\d+),(\d+): (\d+)x(\d+)" x))]
    ; (println order)
    (if order
      (apply assoc {} (interleave [:id :x_offset :y_offset :x :y] (map #(java.lang.Integer/parseInt %) order)))
      nil
    ) 
  )
)

(defn assemble-order [order]
  (let [{ id :id 
          i  :x_offset j :y_offset 
          x  :x y :y } order ] 
         { :id id 
           :a i :b (- (+ x i) 1) 
           :c j :d (- (+ y j) 1)})
)
; (and (>= ua vb) (<= ub vb))
(defn is-in [u v]
  (let [{ uid :id 
          ua :a ub :b 
          uc :c ud :d } u 
        { vid :id 
          va :a vb :b 
          vc :c vd :d } v]
          ; (println "overlap test" u v (and (>= uc vc)  (<= uc vd) (>= ua va) (<= ub vb)) )
          (or (and (>= uc vc)  (<= uc vd) (>= ua va) (<= ua vb))
              (and (>= ud vc)  (<= uc vd) (>= ub va) (<= ub vb)))
    )
)

(defn overlaps [u v]
  (or (is-in u v) (is-in v u) ) 
)
(def data (map assemble-order (numbers-do  (partial map load-order))))

; (def coord (map (fn [i] (map (fn [j] {:a i :b (inc i) :c j :d (inc j) })   (range (- (reduce max (map :d data)) 0)))) (range (- (reduce max (map :b data) ) 0) ) ))

; (println (loop [[h & t] (flatten coord) res 0 i 0]
;     (if (= (mod i 10000) 0) (println (/ (- 990000 i) 990000) i h ))
;     ; (if h
;     ; (println h ) 
;     (if (nil? h)
;         res
;         (recur t (+ res (if (> (count (filter #(if (overlaps h %) 1 nil) data)) 1) 1 0)) (inc i)))  
;   ))

; (print (filter some? (map #(:id (if (= (count (filter (partial overlaps %) (filter (fn [k] (> (:id k) (:id %))) data))) 0) %)) data)))


(defn poverlap [x] (filter (partial overlaps x) data))

(println (map :id (filter #(< (count (poverlap %)) 2)  data)))

; (println (map (fn [x] (reduce conj (list) x)) data))