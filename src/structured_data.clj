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
    (assoc book :authors (conj existing new-author))))

(def mybook {:title "Bar" :authors #{(hash-map :name "Richard" :birth-year 1963)
                                    (hash-map :name "Donald"  :birth-year 1962)}})

(mybook :authors)

(def yourbook {:title "Bar" :authors #{(hash-map :name "Coco" :birth-year 2007)}})

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

(defn monotonic? [a-seq]
  (let [non-descending (fn [x] (apply <= x))
        non-ascending  (fn [x] (apply >= x))]
    (or (non-ascending a-seq) (non-descending a-seq))))

(defn stars [n]
  (apply str (repeat n "*")))

(defn toggle [a-set elem]
  (if (contains? a-set elem) (disj a-set elem) (conj a-set elem)))

(defn contains-duplicates? [a-seq]
  (not= (count a-seq) (count (set a-seq))))

(defn old-book->new-book [book]
  (assoc book :authors (set (:authors book))))

(defn has-author? [book author]
  (contains? (:authors book) author))

(defn authors [books]
  (let [auths (map :authors books)]
    (apply clojure.set/union auths)))


(clojure.set/union (set (map :authors bookshelf)))

(defn all-author-names [books]
  (set (map :name (authors books))))

(set (map :name (authors bookshelf)))

(defn author->string [author]
  (let [years (fn [author] (str " (" (:birth-year author) " - " (:death-year author) ")"))
        not-nil (complement nil?)
        have_dates (fn [author]
                     (or (not-nil (:death-year author))
                          (not-nil (:birth-year author))))
        name (fn [author] (:name author))]
    (str (name author) (if (have_dates author) (years author)))))

(author->string {:name "Richard" :birth-year 1963 :death-year 2018})


(defn authors->string [authors]
  (apply str (interpose ", " (map author->string authors))))

(authors->string (authors bookshelf))

(def rmp {:name "Richard Palmer" :birth-year 1963 :death-year 2018})
(def dgp {:name "Donald Palmer"})
(def newbook {:title "Clojure" :authors [rmp dgp]})

(def sequel {:title "More Clojure" :authors [rmp]})

(defn book->string [book]
  (str (:title book) ", written by " (authors->string (:authors book))))

(def annex [newbook sequel])

(defn books->string [books]
  (let [cnt (count books)
        ;;detail (fn [b] (str (book->string b)))
        ;;all (fn [bs] (apply str (interpose ", " (map detail bs))))
        details (fn [bs] (apply str (interpose ". " (map book->string bs))))]
    (case cnt
      0 "No books."
      1 (str "1 book. " (details books) ".")
      (str cnt " books. " (details books) "."))))

(books->string annex)

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
