(ns nubank.workspaces.ui.highlight
  (:require [com.fulcrologic.fulcro.components :as fp]
            [com.fulcrologic.fulcro-css.localized-dom :as dom]
            ["highlight.js" :refer [highlightElement]]))

(fp/defsc Highlight [this {::keys [source language]}]
  {:componentDidMount
   (fn [this]
     (highlightElement (dom/node this)))}

  (dom/pre {:className (or language "clojure")} source))

(def highlight (fp/factory Highlight))
