<?php
class sudoku
{
	private $_map;
	private $_size=2;
	private $_derang;

	public function index($size=2)
	{
		$this->_size=$size;
		$map=$this->_create_map();

		echo '<table>'
		for($num=0,$total=$this->_size*$this->_size; $num<$total; $num++){
			echo '<tr>';
			for($i=0; $i<$this->_size; $i++){
				for($j=0; $j<$this->_size; $j++)
					echo '<td>'.$map[$num][$i][$j].'</td>';
			}
			echo '</tr>';
		}

		echo '</table>';
	}

	private function _create_map()
	{
		//generate the derangements seed.
		$this->_derang=$this->_generate_derang($this->_size);
		for($i=0; $i<$this->_size*$this->_size; $i++)
			$range[]=$i+1;
		shuffle($range);
		for($i=0; $i<$this->_size; $i++)
			for($j=0; $j<$this->_size; $j++)
				$this->_map[0][$i][$j]=$range[$i*$this->_size+$j];

		for($i=0; $i<$this->_size; $i++){
			//rotate the first matrix of every row
			if($i!=0)
				$this->_map[$i*$this->_size]=$this->_column_rotate(0,$i);
			for($j=1; $j<$this->_size; $j++){
				//do the rest matrixs
				$this->_map[$i*$this->_size+$j]=$this->_row_rotate($i*$this->_size,$j);
			}
		}
	}
	//to do:need design an algorithm to generate a dimagram dynamiclly.
	private function _generate_derang($size)
	{
		if($size==2)
			return array(array(0,1),array(1,0));
		else if($size==3)
			return array(array(0,1,2),array(1,2,0),array(2,0,1));
		else
			return;
	}
	private function _column_rotate($map_idx,$num)
	{
		for($i=0; $i<$this->_size; $i++)
			for($j=0; $j<$this->_size; $j++){
				$temp[$i][$j]=$this->_map[$map_idx][$i][$this->_derang[$num][$j]];
			}
		return $temp;
	}
	private function _row_rotate($map_idx,$num)
	{
		for($i=0; $i<$this->_size; $i++)
			for($j=0; $j<$this->_size; $j++)
				$temp[$i][$j]=$this->_map[$map_idx][$this->_derang[$num][$i]][$j];
		return $temp;
	}
}
