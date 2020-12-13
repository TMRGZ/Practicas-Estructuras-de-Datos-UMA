import Test.QuickCheck

divideA :: Integer -> Integer -> Bool
divideA x y = mod y x ==0

divisores :: Integer -> [Integer]
-- divisores naturales (>=0) de un entero
divisores x = [n | n<-[1..x], divideA n x]

comunDivisor :: Integer -> Integer -> [Integer]
comunDivisor x y = [n | n <-divisores x, divideA n y]

maxComunDivisor :: Integer -> Integer -> Integer
maxComunDivisor x y = maximum (comunDivisor x y)

minComunMultiplo :: Integer -> Integer -> Integer
minComunMultiplo x y = div (x*y) (maxComunDivisor x y)