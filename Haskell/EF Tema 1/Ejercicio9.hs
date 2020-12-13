import Test.QuickCheck

(~=) :: Double -> Double -> Bool
x ~= y = abs (x-y) < epsilon
    where epsilon = 1/1000


unEuro :: Double
unEuro = 166.386
    
pesetasAEuros :: Double -> Double
pesetasAEuros x = x/unEuro
    
eurosAPesetas :: Double -> Double
eurosAPesetas x = x*unEuro

p_inversas x = eurosAPesetas (pesetasAEuros x) ~= x