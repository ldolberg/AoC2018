(defn deque 
  ([]
   '[()()])
  ([& elems]
   [elems '()]))

(defn push-front [deque elem]
  (let [[head tail] deque]
    [(cons elem head) tail]))

(defn push-back [deque elem]
  (let [[head tail] deque]
    [head (cons elem tail)]))

(defn pop-front [deque]
  (let [[head tail] deque]
    (if (empty? head)
      [(-> tail reverse rest) head]
      [(rest head) tail])))

(defn pop-back [deque]
  (let [[head tail] deque]
    (if (empty? tail)
      [tail (-> head reverse rest)]
      [head (rest tail)])))

(defn peek-front [deque]
  (let [[head tail] deque]
    (if (empty? head)
      (-> tail reverse first)
      (first head))))

(defn peek-back [deque]
  (let [[head tail] deque]
    (if (empty? tail)
      (-> head reverse first)
      (first tail))))

(defn size [deque]
    (reduce + (map count deque))
)

(defn rotate_right [deque]
    (if (zero? (size deque)) 
        deque
        (push-back (pop-front deque) (peek-front deque))))


(defn rotate_left [deque]
    (push-front (pop-back deque) (peek-back deque))    
)

(defn rotate_left_n [deque n]
    (if (= n 1)
            (rotate_left deque)
            (rotate_left_n (rotate_left deque) (dec n))))

(defn elems [deque]
    (let [[head tail] deque]
    (concat 
        head
        tail)))

(defn test-dq [] (elems (let [dq (deque )]
    (-> dq 
        (push-front 4)
        (push-front 3)
        (push-front 2)
        (push-front 1)
        rotate_right
        rotate_right
        (push-front 5)
        rotate_left
        ))))