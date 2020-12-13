import Test.QuickCheck

divideA :: Integer -> Integer -> Bool
divideA x y = mod y x ==0

divisores :: Integer -> [Integer]
-- divisores naturales (>=0) de un entero
divisores x = [n | n<-[1..x], divideA n x]

esPrimo :: Integer -> Bool
esPrimo x = divisores x == [1,x]

primosHasta :: Integer -> [Integer]
primosHasta x = [n| n<- [1..x], esPrimo n]

pares :: Integer -> [(Integer, Integer)]
pares x = [(n,t) | n <- [1..x], t <- [1..n], esPrimo n, esPrimo t, n+t==x]

goldbach :: Integer -> Bool
goldbach x
    | (x>2)&&((mod x 2)==0) = True
    | otherwise = False

goldbachHasta :: Integer -> Bool
goldbachHasta x = [n | n <- [2..x], mod (n 2) ==0]