iguales3 :: Eq a => (a,a,a) -> Bool
iguales3 (x,y,z)
    |x==y&&y==z = True
    |otherwise = False