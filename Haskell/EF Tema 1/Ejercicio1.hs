import Test.QuickCheck

cuadrado :: Integer -> Integer
cuadrado x = x*x

suma :: Integer -> Integer -> Integer
suma x y = cuadrado x + cuadrado y

resta :: Integer -> Integer -> Integer
resta x y = cuadrado x - cuadrado y

cuadratica :: Integer -> Integer -> Integer
cuadratica x y = 2*x*y

esTerna :: Integer -> Integer -> Integer -> Bool
esTerna x y z = suma x y == cuadrado z

calculaTerna :: Integer -> Integer -> (Integer, Integer, Integer)
calculaTerna x y = (resta x y, cuadratica x y, suma x y)

terna :: Integer -> Integer -> (Integer, Integer, Integer)
terna x y = if x>y then calculaTerna x y else (-1, -1,-1)

p_ternas :: Integer -> Integer -> Property
p_ternas x y = x>0 && y>0 && x>y ==> esTerna l1 l2 h  
    where (l1,l2,h) = terna x y 
