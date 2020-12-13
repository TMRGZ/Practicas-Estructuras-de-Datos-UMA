entre :: Ord a => a -> (a,a) -> Bool
entre x (me,ma)
    |me<x&&x<ma = True
    |otherwise = False