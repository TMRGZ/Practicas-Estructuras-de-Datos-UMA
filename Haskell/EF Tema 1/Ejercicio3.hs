import Test.QuickCheck

ordena2 :: Ord a => (a,a) -> (a,a)
ordena2 (x,y) = if x<y then (x,y) else (y,x)

ordena3 :: Ord a => (a,a,a) -> (a,a,a)
ordena3 (x,y,z) = if x<y && x<z
  then if y<z
    then (x,y,z)
    else (x,z,y)
  else if y<x && y<z
    then if x<z
      then (y,x,z)
      else (y,z,x)
    else if z<y && z<x
      then if y<x
        then (z,y,x)
        else (z,x,y)
      else (x,x,x)

p1_ordena2 :: Ord a => a -> a -> Bool
p1_ordena2 x y = enOrden (ordena2 (x,y))   
  where enOrden (x,y) = x<=y  
  
p2_ordena2 :: Ord a => a -> a -> Bool
p2_ordena2 x y = mismosElementos (x,y) (ordena2 (x,y))  
  where
    mismosElementos (x,y) (z,v) = (x==z && y==v) || (x==v && y==z) 

p_ordena3 :: (Ord a) => a -> a -> a -> Bool
p_ordena3 x y z = enOrden (ordena3 (x,y,z))
    where enOrden (x,y,z) = x<=y&&y<=z
