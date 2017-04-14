public class Test
{
	public static void main(String[] args)
	{
		int[] indices = {
				0,1,2,3,4,	//face 1
				0,5,6,7,1,	//face 2
				1,7,8,9,2,	//face 3
				2,9,10,11,3,	//face 4
				3,11,12,13,4,	//face 5
				4,13,14,5,0,	//face 6
				5,14,15,16,6,	//face 7
				7,6,17,18,8,	//face 8
				9,8,18,19,10,	//face 9
				11,12,20,19,10,	//face 10
				13,14,15,20,12, //face 11
				15,20,19,18,17	//face 12
		};
		
			float a = 1;
			float c = (float)(1 + Math.sqrt(5))/2; //golden ratio
			float b = 1/c;
			
           		float[] dodecahedron_vertices = {   // Coordinates
				//face 1
				-a,-a,-a,	0,-b,-c,	a,-a,-a,	b,-c,0,		-b,-c,0,
				//face 2
				-c,0,-b,	-a,a,-a,	0,b,-c,	
				//face 3
				a,a,-a,		c,0,-b,
				//face 4
				c,0,b,		a,-a,a,
				//face 5
				0,-b,c,		-a,-a,a,
				//face 6
				-c,0,b,				
				//face 7
				-a,a,a,		-b,c,-0,				
				//face 8
				-b,c,0,		b,c,0,			
				//face 9
				a,a,a,				
				//face 10
				0,b,c				
				//face 11 and 12 have no unique points
			}; //20 vertices total, 60 values total
			
			float[] normals = new float[180]; //three per point
	
		float normX,normY,normZ;
		float[] vecA = new float[3];
		float[] vecB = new float[3];
	
		for(int i=0;i<12;i++)
		{
			for(int j=0;j<5;j++)
			{
				/*
				System.out.println(dodecahedron_vertices[indices[(i*5 + j)]*3 + 0]); //y
				System.out.println(dodecahedron_vertices[indices[(i*5 + j)]*3 + 1]); //x
				System.out.println(dodecahedron_vertices[indices[(i*5 + j)]*3 + 2]); //z
				System.out.println();*/
				
				//take a point then take any two other points to make two vectors
				//then calculate normal
				
				//case 1: start (j == 0), take next two points
				if(j == 0)
				{
					vecA[0] = dodecahedron_vertices[indices[(i*5 + j) + 1]*3 + 0]-dodecahedron_vertices[indices[(i*5 + j)]*3 + 0];
					vecA[1] = dodecahedron_vertices[indices[(i*5 + j) + 1]*3 + 1]-dodecahedron_vertices[indices[(i*5 + j)]*3 + 1];
					vecA[2] = dodecahedron_vertices[indices[(i*5 + j) + 1]*3 + 2]-dodecahedron_vertices[indices[(i*5 + j)]*3 + 2];
					
					vecA[0] = dodecahedron_vertices[indices[(i*5 + j) + 2]*3 + 0]-dodecahedron_vertices[indices[(i*5 + j)]*3 + 0]; 
					vecA[1] = dodecahedron_vertices[indices[(i*5 + j) + 2]*3 + 1]-dodecahedron_vertices[indices[(i*5 + j)]*3 + 1];
					vecA[2] = dodecahedron_vertices[indices[(i*5 + j) + 2]*3 + 2]-dodecahedron_vertices[indices[(i*5 + j)]*3 + 2];
				}
				
				//case 2: end (j == 4), take previous two points
				else if(j == 4)
				{
					vecA[0] = dodecahedron_vertices[indices[(i*5 + j) - 1]*3 + 0]-dodecahedron_vertices[indices[(i*5 + j)]*3 + 0];
					vecA[1] = dodecahedron_vertices[indices[(i*5 + j) - 1]*3 + 1]-dodecahedron_vertices[indices[(i*5 + j)]*3 + 1];
					vecA[2] = dodecahedron_vertices[indices[(i*5 + j) - 1]*3 + 2]-dodecahedron_vertices[indices[(i*5 + j)]*3 + 2];
					
					vecA[0] = dodecahedron_vertices[indices[(i*5 + j) - 2]*3 + 0]-dodecahedron_vertices[indices[(i*5 + j)]*3 + 0]; 
					vecA[1] = dodecahedron_vertices[indices[(i*5 + j) - 2]*3 + 1]-dodecahedron_vertices[indices[(i*5 + j)]*3 + 1];
					vecA[2] = dodecahedron_vertices[indices[(i*5 + j) - 2]*3 + 2]-dodecahedron_vertices[indices[(i*5 + j)]*3 + 2];
				}
				
				//else case: in between, take next and previous
				else
				{
					vecA[0] = dodecahedron_vertices[indices[(i*5 + j) + 1]*3 + 0]-dodecahedron_vertices[indices[(i*5 + j)]*3 + 0];
					vecA[1] = dodecahedron_vertices[indices[(i*5 + j) + 1]*3 + 1]-dodecahedron_vertices[indices[(i*5 + j)]*3 + 1];
					vecA[2] = dodecahedron_vertices[indices[(i*5 + j) + 1]*3 + 2]-dodecahedron_vertices[indices[(i*5 + j)]*3 + 2];
					
					vecA[0] = dodecahedron_vertices[indices[(i*5 + j) - 1]*3 + 0]-dodecahedron_vertices[indices[(i*5 + j)]*3 + 0];
					vecA[1] = dodecahedron_vertices[indices[(i*5 + j) - 1]*3 + 1]-dodecahedron_vertices[indices[(i*5 + j)]*3 + 1];
					vecA[2] = dodecahedron_vertices[indices[(i*5 + j) - 1]*3 + 2]-dodecahedron_vertices[indices[(i*5 + j)]*3 + 2];
				}
				
				normX = vecA[1]*vecA[2] - vecB[2]*vecB[2];
				normY = vecA[2]*vecA[0] - vecB[0]*vecB[2];
				normZ = vecA[0]*vecA[1] - vecB[1]*vecB[0];
				
				normals[((i*5 + j)*3 + 0)] = normX;
				normals[((i*5 + j)*3 + 1)] = normX;
				normals[((i*5 + j)*3 + 2)] = normX;
				
				for(int k = 0;k<3;k++)
				{
					System.out.print(normals[((i*5 + j)*3 + k)] + " ");
				}
				System.out.println();
			}
			System.out.println("\n");
		}
	}
}

