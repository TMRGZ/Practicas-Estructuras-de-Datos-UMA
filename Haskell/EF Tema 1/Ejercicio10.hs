import Test.QuickCheck

cuadrado :: Double -> Double
cuadrado x = x*x

raizEcuacion :: Double -> Double -> Double -> Double
raizEcuacion x y z
     | real >=0 = ((cuadrado y) -4*x*z)/(2*x)
     | otherwise = error "Raices no reales"
     where real = cuadrado y - (4*x*y)

parteIzquierda :: Double -> Double -> Double
parteIzquierda x y = (-y)/(2*x)

raices :: Double -> Double ->Double -> (Double, Double)
raices x y z = (izq+dr, izq-dr)
    where 
        izq = parteIzquierda x y
        dr = raizEcuacion x y z

infix 4 ~=
(~=) :: Double -> Double -> Bool
x~=y = abs(x-y) < epsilon
    where epsilon = 1/1000


p1_raices a b c = a/=0 && (cuadrado b - (4*a*b)>=0)  ==> esRaiz r1 && esRaiz r2
    where
        (r1,r2) = raices a b c
        esRaiz r = a*(r^2) + b*r +c == 0       