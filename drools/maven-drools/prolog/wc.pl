[user].

matched_com(a,b).
matched_ind(a,b).

strongest(X,Y) :- matched_com(X,Y), matched_ind(X,Y).
strong(X,Y) :- matched_com(X,Y); matched_ind(X,Y).
weak(X,Y) :- not(strongest(X,Y)), not(strong(X,Y)).

#