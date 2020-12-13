esMultiplo :: Integer -> Integer -> Bool
esMultiplo x y
    |mod x y == 0 = True
    |otherwise = False