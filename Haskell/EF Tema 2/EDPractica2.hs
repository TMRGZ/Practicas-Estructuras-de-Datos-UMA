-------------------------------------------------------------------------------
-- Estructuras de Datos. 2º Curso. ETSI Informática. UMA
--
-- PRACTICA 2ª (Características de la Programación Funcional)
--
-- (completa y sustituye los siguientes datos)
-- Titulación: Grado en Ingeniería Informatica.
-- Alumno: Ruiz Gomez, Miguel Angel
-- Fecha de entrega:  DIA | MES | AÑO
--
-- Ejercicios resueltos de la Relación : ..........
--
-------------------------------------------------------------------------------
module Practica2 where
import Test.QuickCheck


-------------------------------------------------------------------------------
-- Ejercicio 2
-------------------------------------------------------------------------------

maximoYResto :: Ord a => [a] -> (a,[a])
-- Devuelve el maximo de una lista y el resto de la lista (sin el maximo)
-- A El resto de los elementos pueden aparecer en cualquier orden
maximoYResto [] = error "Lista vacia"
maximoYResto [x] = (x,[])
maximoYResto (x:xs)
  |x>=m = (x,m:rs)
  |otherwise = (m, x:rs)
  where (m,rs) = maximoYResto (xs)

-- sigue aqui
-- B Escribe la funcion ordenaD que ordena descendentemente una lista utilizando la función anterior

ordenaD :: Ord a => [a] -> [a]
ordenaD (x:xs) = m: (ordenaD rs)
  where (m,rs) = maximoYResto (x:xs)

-------------------------------------------------------------------------------
-- Ejercicio 3
-------------------------------------------------------------------------------

reparte :: [a] -> ([a], [a])
reparte [] = ([],[])
reparte [x] = ([x], [])
reparte (x:y:xs) = (x:us, y:vs)
  where (us, vs) = reparte xs

   
-------------------------------------------------------------------------------
-- Ejercicio 4
-------------------------------------------------------------------------------
distintos :: Ord a => [a] -> Bool
distintos [] = True
distintos (x:xs) = not (elem x xs) && distintos xs
    
-------------------------------------------------------------------------------
-- Ejercicio 6
-------------------------------------------------------------------------------

divisores :: Integer -> [Integer]
-- divisores naturales (>=0) de un entero
divisores x = [n | n<-[1..x], divideA n x]
  
divisores' :: Integer -> [Integer]
-- divisores enteros (positivos y negativos) de un entero
divisores' x = [n | n<-[-x..x],n/=0, divideA n x]

divideA :: Integer -> Integer -> Bool
divideA x y = mod y x ==0
    
-------------------------------------------------------------------------------
-- Ejercicio 8
-------------------------------------------------------------------------------
esPrimo :: Integer -> Bool
esPrimo x = divisores x == [1,x]

primosHasta :: Integer -> [Integer]
primosHasta x = [n| n<- [1..x], esPrimo n]

primosHasta' :: Integer -> [Integer]
primosHasta' x = filter esPrimo [1..x]

-------------------------------------------------------------------------------
-- Ejercicio 10
-------------------------------------------------------------------------------
esPerfecto :: Integer -> Bool
esPerfecto x = sum (divisores x) == 2*x

perfectosMenoresQue :: Integer -> [Integer]
perfectosMenoresQue x = [n | n<-[1..x], esPerfecto n]

-------------------------------------------------------------------------------
-- Ejercicio 11
-------------------------------------------------------------------------------
take' :: (Enum a, Num a) => a -> [t] -> [t]
take' n xs = [x | (p,x) <- zip [0..(n-1)] xs]

drop' :: Int -> [t] -> [t]
drop' n xs = [x | (p,x) <- zip[0..length xs] xs, p>n]

-------------------------------------------------------------------------------
-- Ejercicio 13
-------------------------------------------------------------------------------
desconocida :: (Ord a) => [a] -> Bool
desconocida xs = and [ x<=y | (x,y) <- zip xs (tail xs) ]
-- Qué hace?
  -- Decir si esta ordenada
-------------------------------------------------------------------------------
-- Ejercicio 14
-------------------------------------------------------------------------------
-- apartados a, b, e y f
-- a)
inserta :: (Ord a) => a -> [a] -> [a]
inserta x xs = (takeWhile (<=x) xs ++ [x]) ++ dropWhile (<=x) xs


-- b)
inserta' :: (Ord a ) => a -> [a] -> [a]
inserta' x [] = [x]
inserta' x (y:ys)
  | x<=y = x:y:ys
  | otherwise = y:inserta' x ys

-- e)

ordena :: (Ord a) => [a] -> [a]
ordena = foldr inserta' []

-- f)  Utiliza para ello la función sorted definida en las transarencias


-------------------------------------------------------------------------------
-- Ejercicio 15
-------------------------------------------------------------------------------
geometrica :: Num a => a -> a -> [a]
geometrica base factor = iterate (*factor) base

multiplosDe :: Num a => a -> [a]
multiplosDe x = iterate (+x) 0

potenciasDe :: Num a => a -> [a]
potenciasDe x = iterate (*x) 1

-------------------------------------------------------------------------------
-- Ejercicio 24
-------------------------------------------------------------------------------

binarios ::Integer -> [String]
binarios 0 = [[]]
binarios x 
  | x > 0 = (map ('0':) (binarios(x-1))) ++ (map ('1':) (binarios(x-1)))

-------------------------------------------------------------------------------
-- Ejercicio 37
-------------------------------------------------------------------------------

type Izdo = Double
type Dcho = Double
type Epsilon = Double
type Función = Double -> Double
biparticion :: Función -> Izdo -> Dcho -> Epsilon -> Double

biparticion f a b epsilon
  | long < epsilon    = undefined
-- sigue aqui
  where
      long = b - a
-- sigue aqui
