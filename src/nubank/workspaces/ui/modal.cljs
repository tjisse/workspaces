(ns nubank.workspaces.ui.modal
  (:require ["react-dom" :refer [createPortal]]
            [com.fulcrologic.fulcro-css.localized-dom :as dom]
            [com.fulcrologic.fulcro.components :as fp]
            [nubank.workspaces.ui.events :as events]))

(fp/defsc WidgetContent [this props]
  {:css [[:.container {:max-height "70vh"
                       :overflow   "auto"}]]}
  (dom/div :.container props
    (fp/children this)))

(def widget-content (fp/factory WidgetContent))

(fp/defsc Modal [this {::keys [on-close]
                       :or    {on-close identity}}]
  {:css         [[:.background {:position        "fixed"
                                :left            0
                                :top             0
                                :background      "rgba(0, 0, 0, 0.5)"
                                :width           "100vw"
                                :height          "100vh"
                                :display         "flex"
                                :align-items     "center"
                                :justify-content "center"
                                :z-index         "100"
                                :overflow-y      "scroll"}]
                 [:.container {:display        "flex"
                               :flex-direction "column"
                               :max-width      "90vw"
                               :max-height     "80vh"}]
                 [:.close {:align-self     "flex-end"
                           :color          "white"
                           :cursor         "pointer"
                           :font-size      "10px"
                           :text-transform "uppercase"}]]
   :css-include [WidgetContent]}
  (createPortal
    (dom/div
      (events/dom-listener {::events/keystroke "escape"
                            ::events/action    on-close})
      (dom/div :.background {:onClick (fn [e]
                                        (if (= (.-currentTarget e) (.-target e))
                                          (on-close e)))}
               (dom/div :.container
                        (dom/div
                          (fp/children this))))) js/document.body))

(def modal (fp/factory Modal))
