(defun my_last (lista) lista)

(defun my_last (lista)
  (if (null lista)
      nil
      (if (null (rest lista))
	     lista
         (my_last (rest lista))
      )
  )
)