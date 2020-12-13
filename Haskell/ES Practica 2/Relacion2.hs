-------------------------------------------------------------------------------
-- Estructuras de Datos. 2º Curso. ETSI Informática. UMA
--
-- PRACTICA 2ª (Características de la Programación Funcional)
--
-- (completa y sustituye los siguientes datos)
-- Titulación: Grado en Ingeniería …………………………………… [Informática | del Software | de Computadores].
-- Alumno: APELLIDOS, NOMBRE
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
maximoYResto []   = error "Lista vacia"
maximoYResto [x]  = (x, [])
maximoYResto (x:xs)
  | x>=m      = (x, m:rs)
  | otherwise = (m, x:rs)
    where (m, rs) = maximoYResto (xs)
-- sigue aqui
-- B Escribe la funcion ordenaD que ordena descendentemente una lista utilizando la función anterior
ordenaD :: Ord a => [a] -> [a]
ordenaD [x] = [x]
ordenaD (x:xs)  = m : ordenaD rs
  where (m, rs) = maximoYResto (x:xs)

-------------------------------------------------------------------------------
-- Ejercicio 3
-------------------------------------------------------------------------------
reparte :: [a] -> ([a], [a])
reparte [] = ([],[])
reparte [x] = ([x], [])
reparte (x:y:xs) = (x:li, y:ld)
  where (li, ld) = reparte xs

-------------------------------------------------------------------------------
-- Ejercicio 4
-------------------------------------------------------------------------------
distintos :: Ord a => [a] -> Bool
distintos [] = True
distintos (x:xs) = not (elem x xs) && distintos xs


-------------------------------------------------------------------------------
-- Ejercicio 6
-------------------------------------------------------------------------------
esMultiplo :: Integer -> Integer -> Bool
esMultiplo x y = mod x y == 0

divideA :: Integer -> Integer -> Bool
x `divideA` y = esMultiplo y x

divisores :: Integer -> [Integer]
-- divisores naturales (>=0) de un entero
divisores x = [d | d<- [1..x], d `divideA` x]

divisores' :: Integer -> [Integer]
-- divisores enteros (positivos y negativos) de un entero
divisores' x = [d | d<- [-x..x], d/=0 ,d `divideA` x]

-------------------------------------------------------------------------------
-- Ejercicio 8
-------------------------------------------------------------------------------
-- esPrimo
esPrimo :: Integer -> Bool
esPrimo x = length (divisores x) == 2
-- primosHasta
primosHasta :: Integer -> [Integer]
primosHasta x = [p | p <- [1..x], esPrimo p]
-- primosHasta'
primosHasta' :: Integer -> [Integer]
primosHasta' x = filter esPrimo [1..x] 

-------------------------------------------------------------------------------
-- Ejercicio 10
-------------------------------------------------------------------------------
sumaLista :: [Integer] -> Integer
sumaLista [] = 0
sumaLista (x:xs) = x + sumaLista xs

sumaFactoresPropios :: Integer -> Integer
sumaFactoresPropios x = sumaLista [v | v <- divisores x, v/=x]

-- esPerfecto
esPerfecto :: Integer -> Bool
esPerfecto x = x == sumaFactoresPropios x
-- perfectosMenoresQue
perfectosMenoresQue :: Integer -> [Integer]
perfectosMenoresQue x = [v | v <- [1..x-1], esPerfecto v]

-------------------------------------------------------------------------------
-- Ejercicio 11
-------------------------------------------------------------------------------
-- take'
take' :: Int -> [a] -> [a]
take' n xs = [x | (p,x) <- zip [0..n-1] xs]
-- drop'
drop' :: Int -> [a] -> [a]
drop' n xs = [x | (p,x) <- zip [0.. length xs] xs, p>=n]

-------------------------------------------------------------------------------
-- Ejercicio 13
-------------------------------------------------------------------------------
desconocida :: (Ord a) => [a] -> Bool
desconocida xs = and [ x<=y | (x,y) <- zip xs (tail xs) ]
-- Qué hace?
-- Comprueba que esta ordenada
-------------------------------------------------------------------------------
-- Ejercicio 14
-------------------------------------------------------------------------------
-- apartados a, b, e y f
-- a)
inserta :: (Ord a) => a -> [a] -> [a]
inserta x s = ((takeWhile (<x) s) ++ [x]) ++ (dropWhile (<x) s)


-- b)
inserta' :: (Ord a ) => a -> [a] -> [a]
inserta' x [] = [x]
inserta' x (y:ys)
  | x <= y = x:y:ys
  | otherwise = y: inserta x ys

-- e)
ordena :: (Ord a) => [a] -> [a]
ordena xs = foldr (\e dest -> (inserta e dest)) [] xs

-- f)  Utiliza para ello la función sorted definida en las transarencias


-------------------------------------------------------------------------------
-- Ejercicio 15
-------------------------------------------------------------------------------
-- geometrica
geometrica :: Int -> Int -> [Int]
geometrica x y = iterate (*y) x
-- multiplosDe
-- potenciasDe

-------------------------------------------------------------------------------
-- Ejercicio 24
-------------------------------------------------------------------------------
binarios ::Integer -> [String]
binarios 0 = [""]
binarios x | x > 0 = ((map ('0':) (binarios (x-1)))) ++ ((map ('1':) (binarios (x-1))))

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
