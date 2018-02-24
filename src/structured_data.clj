(ns structured-data)

(defn do-a-thing [x]
  (let [sum (+ x x)]
    (Math/pow sum sum)))

(defn spiff [v]
  ( let [lhs (get v 0)
         rhs (get v 2)]
    (+ (if (nil? lhs) 0 lhs)
       (if (nil? rhs) 0 rhs))))

(defn cutify [v]
  (conj v "<3"))

(defn spiff-destructuring [v]
  (let [[lhs _ rhs] v]
    (+ lhs rhs)))

(defn point [x y]
  [x y])

(defn rectangle [bottom-left top-right]
  [bottom-left top-right])

(def foo (rectangle (point 1 2) (point 9 10)))

(defn width [[[x1 _] [x2 _]]]
  (- x2 x1))

(width foo)

(defn height [[[_ y1] [_ y2]]]
  (- y2 y1))

(defn square? [rectangle]
  (if (= (width rectangle) (height rectangle)) true false))

(defn area [rectangle]
  (* (width rectangle) (height rectangle)))

(defn contains-point? [rectangle point]
  (let [[[x1 y1] [x2 y2]] rectangle
        [px py] point]
    (and (<= x1 px x2) (<= y1 py y2))))

(contains-point? (rectangle (point 1 1) (point 9 17)) (point 5 5))

(defn contains-rectangle? [outer inner]
  (let [[ll ur] inner]
    (and (contains-point? outer ll) (contains-point? outer ur))))

(contains-rectangle? (rectangle (point 1 1) (point 9 17))
                     (rectangle (point 3 2) (point 6 8)))


(defn title-length [book]
  (count (:title book)))

(defn author-count [book]
  (count (:authors book)))

(defn multiple-authors? [book]
  (if (> (author-count book) 1) true false))

(defn add-author [book new-author]
  (let [existing (:authors book)]
    ( book :authors (conj existing new-author))))

(def mybook {:title "Bar" :authors [(hash-map :name "Richard" :birth-year 1963)
                                    (hash-map :name "Donald"  :birth-year 1962)]})

(mybook :authors)

(def yourbook {:title "Bar" :authors [(hash-map :name "Coco" :birth-year 2007)]})

(add-author mybook (hash-map :name "Roxanne" :birth-year 1968))

(clojure.pprint/pprint mybook)

(author-count yourbook)
(author-count mybook)
(multiple-authors? mybook)

(first (:authors mybook))

(map :birth-year (:authors mybook))


(defn alive? [author]
  (not (contains? author :death-year)))

(defn element-lengths [collection]
  (map count collection))

(element-lengths ["foo" "garbage" "jo"])

(defn second-elements [collection]
  (let [second (fn [x] (get x 1))]
    (map second collection)))

(second-elements [[1 2] [2 3] [3 4]]) ;=> (2 3 4)
(second-elements [[1 2 3 4] [1] ["a" "s" "d" "f"]])

(def bookshelf [mybook yourbook])

(defn titles [books]
  (map :title books))

(titles bookshelf)

(apply + [1 2 3])

(defn monotonic? [a-seq]
  (apply < a-seq))

(monotonic? (range 3))

(defn stars [n]
  (apply str (repeat n "*")))


(defn toggle [a-set elem]
  :-)

(defn contains-duplicates? [a-seq]
  :-)

(defn old-book->new-book [book]
  :-)

(defn has-author? [book author]
  :-)

(defn authors [books]
  :-)

(defn all-author-names [books]
  :-)

(defn author->string [author]
  :-)

(defn authors->string [authors]
  :-)

(defn book->string [book]
  :-)

(defn books->string [books]
  :-)

(defn books-by-author [author books]
  :-)

(defn author-by-name [name authors]
  :-)

(defn living-authors [authors]
  :-)

(defn has-a-living-author? [book]
  :-)

(defn books-by-living-authors [books]
  :-)

; %________%
