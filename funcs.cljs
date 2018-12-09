;; (ns main.core (:gen-class))

;; (defn -main [& args] 
;; ;; do stuff here 
;;   (println "Hello World")
;; )

(defn merga [a b]
  (println "merga" a b)
  (if (= (count a) 0) b 
    (if (= (count b) 0) a 
      (if (> (first a) (first b)) 
        (conj (merga a (rest b)) (first b)) 
        (conj (merga (rest a)  b) (first a))))))
;; (merga '(0 2 5) '(1 3 4 6))

;;(conj (first b) (merga a (rest b)))
(defn sorted [l]
  (let [
        a (first l)
        b (first (rest l))]
    (if (nil? b) (list a)
    (if (> a b) (list b a) (list a b)))))

(defn ms [l]
  (println "ms" l)
  (let [size (count l)
        first_half (take (/ (+ size (mod size 2)) 2) l) 
        second_half (take-last (/ (- size (mod size 2)) 2) l)]
    (if (> size 2) (merga (ms first_half) (ms second_half)) (sorted l))))

;; (ms '(0 2 5 1 3 88 4 -1 100))

(defn abs-diff [a b]
  (if (> a b ) (- a b) (- b  a)))

(defn one-iter [x y]
    (- x (/ (- (* x x) y) (* 2 x))))

(defn newton-method [x y]
  (let [ x_hat (one-iter y x)]
    (if (> (abs-diff x (* x_hat x_hat)) 0.0001) (newton-method x x_hat) x_hat)))

(defn sqrt [x] (newton-method x (* x 2)))

;; (newton-method 2 10)
;; (newton-method 2 5)
;; (newton-method 2 4)



(defn foldr [f l]
    (if (empty? l) l)
    (f (first l) (foldr f (rest l))))


;; (defn is-prime? [x] 
;;   (if (= 0 (mod x 2)) false)
;;     (let [raiz (sqrt x)])
;;     (sqrt)
;; (defn primes []
;;   (filter is-prime? range))

(defn find-min [l]
  (let [ [i & elems] l]
    (if (nil? elems) i (min i (find-min elems)))))

(defn remove-elem [l i]
  (remove i l)
)
  
(defn bubble-sort [l]
  (let [min-elem (find-min [l])]))


(defn node [e l r]
  {:l l :r r :v e})
(import java.lang.Integer)
(defn sumn []
  (println " A ver")
  (let [numbers (map #(java.lang.Integer/parseInt %) (clojure.string/split (clojure.string/replace (slurp "input") #"\+" "") #"\n"))]
    (loop [[head & tail] numbers sum 0]
      ;; (println head (int head))
      (if (nil? head)
          sum
          (recur tail (+ sum (int head))))
    )
  )
)

;; (sumn )

(defn count-rep [l e]
  (loop [[head & tail] l s 0]
    (if (nil? head)
    s
    (recur tail (+ s (if (= head e) 1 0))))
  ))