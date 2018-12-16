; position=< 9,  1> velocity=< 0,  2>
(def fp "day 10/input")

(def input (map #(map (fn[y] (. Integer parseInt y)) (rest (re-find #"position=<\s?(-?\d+),\s+(-?\d+)>\s+velocity=<\s?(-?\d+),\s+(-?\d+)" %  ))) (clojure.string/split (slurp fp) #"\n")))

(def min_x #(first (apply min-key first %)))
(def min_y #(second (apply min-key second %)))
(def max_x #(first (apply max-key first %)))
(def max_y #(second (apply max-key second %)))

(defn move-i [i x] [(+ (first x) (* i (nth x 2))) (+ (second x) (* i (nth x 3)))])

(defn draw [i j col]
    (if (some #(= [j i] %) col) "#" "." ))

(defn area[col]
    (let [  mix (min_x col)
            miy (min_y col) 
            mx (max_x col)
            my (max_y col)]
        (* (inc (- mx mix)) (inc (- my miy))))) 

(defn print-particles [col]
    (let [ mix (min_x col)
           miy (min_y col) 
           mx (max_x col)
           my (max_y col)]
            (map (fn [i] (clojure.string/join " " (map (fn [j] (draw i j col)) (range mix (inc mx))))) (range miy (inc my)))))

(println (loop [x (range) 
                px (area input)]
    (let [[h & t] x
         ac (area (map (partial move-i h) input))]
        (if (> ac px ) 
            (do (println (dec h ) ) (clojure.string/join "\n" (print-particles (map (partial move-i (dec h)) input))))
            (recur t (area (map (partial move-i h) input))) ))
))