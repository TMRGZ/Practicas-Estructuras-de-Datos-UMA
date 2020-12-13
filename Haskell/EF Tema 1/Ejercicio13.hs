esMultiplo :: Integer -> Integer -> Bool
esMultiplo x y
    |mod x y == 0 = True
    |otherwise = False


infixl 0 ==>>
(==>>) :: Bool -> Bool -> Bool
x ==>> y = y

esBisiesto :: Integer -> Bool
esBisiesto x = esMultiplo x 4 &&  (esMultiplo x 100) ==>> (esMultiplo x 400)