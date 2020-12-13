import Test.QuickCheck

-- Ejercicio 1
esTerna :: Integer -> Integer -> Integer -> Bool
esTerna x y z = x^2 + y^2 == z^2

terna :: Integer -> Integer -> (Integer, Integer, Integer)
terna x y = (x^2 - y^2, 2*x*y, x^2 + y^2)

p_ternas x y = x>0 && y>0 && x>y ==> esTerna l1 l2 h
    where (l1, l2, h) = terna x y

-- Ejercicio 2
intercambia :: (a, b) -> (b, a)
intercambia (x, y) = (y, x)

-- Ejercicio 3
ordena2 :: Ord a => (a,a) -> (a,a)
ordena2 (x, y)
    | x > y     = (y, x)
    | otherwise = (x, y)

p1_ordena2 x y = enOrden (ordena2 (x,y))
    where enOrden (x, y) = x<=y

p2_ordena2 x y = mismosElementos (x,y) (ordena2 (x,y))
    where mismosElementos (x,y) (z,v) = (x==z && y==v) || (x==v && y==z)

{-ordena3 :: Ord a => (a,a,a) -> (a,a,a)
ordena3 (x,y,z)
    | x > y && x > z = 
        case () of -- x va la ultima
            | y > z     = (z, y, x)
            | otherwise = (y, z, x)
    | y > x && y > z = 
        case () of -- y va la ultima
            | x > z     = (z, x, y)
            | otherwise = (x, z, y)
    | z > x && z > y = 
        case () of -- z va la ultima
            | y > x     = (x, y, z)
            | otherwise = (y, x, z)
    | otherwise = Nothing -}

-- Ejercicio 4
max2 :: Ord a => a -> a -> a
max2 x y
    | x > y     = x
    | otherwise = y

-- Ejercicio 5
entre :: Ord a => a -> (a, a) -> Bool
entre x (y, z)
    | y <= x && x <= z   = True
    | otherwise         = False

-- Ejercicio 6
iguales3 :: Eq a => (a,a,a) -> Bool
iguales3 (x,y,z)
        | x == y && y == z  = True
        | otherwise         = False

-- Ejercicio 7
type TotalSegundos  = Integer
type Horas          = Integer
type Minutos        = Integer
type Segundos       = Integer
descomponer :: TotalSegundos -> (Horas, Minutos, Segundos)
descomponer x = (horas, minutos, segundos)
        where
            horas       = div x 3600
            minutos     = div (mod x 3600) 60
            segundos    = mod (mod x 3600) 60

-- Ejercicio 8
unEuro :: Double
unEuro = 166.386

pesetasAEuros :: Double -> Double
pesetasAEuros x = x / unEuro

eurosAPesetas :: Double -> Double
eurosAPesetas x = x * unEuro

-- Ejercicio 9
infix 4 ~=
(~=) :: Double -> Double -> Bool
x ~= y = abs(x-y) < epsilon
    where epsilon = 1/1000

-- Ejercicio 10
{-derecha x y z = (sqrt y^2 -4*x*z) / 2*x
izquierda x y = y/2x

raices :: a -> a -> a -> (a, a)
raices x y z = (positivo, negativo)
    where
        positivo = ((izquierda x y) + derecha x y z)
        negativo = (-y - sqrt y^2 -4*x*z) / 2*x-}

-- Ejercicio 11
esMultiplo :: Integer -> Integer -> Bool
esMultiplo x y = mod x y == 0

-- Ejercicio 12 No funcional
esBisiesto :: Integer -> Bool
esBisiesto x
        | mod x 400 == 0 = True
        | mod x 4 == 0 = True
        | otherwise = False

-- Ejercicio 14
potencia :: Integer -> Integer -> Integer
potencia b n
        | n == 0 = 1
        | n > 0 = b * potencia b (n-1)

potencia' :: Integer -> Integer -> Integer
potencia' b n -- No funcional
        | esMultiplo n 2    = potencia' (potencia' b (div n 2)) 2
        | otherwise         = b * (potencia' (potencia' b (div (n-1) 2)) 2)

factorial :: Integer -> Integer
factorial x
        | x == 0 = 1
        | otherwise = x * factorial (x-1)

divideA :: Integer -> Integer -> Bool
x `divideA` y = esMultiplo y x

