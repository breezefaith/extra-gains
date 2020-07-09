#include<iostream>
#include <Eigen/Dense>
#include <ctime>

using namespace Eigen;
using namespace std;

int main()
{
#pragma region 1
	cout << "task 1" << endl;
	Matrix<int, 3, 3> m1, m2;
	m1 << 1, 2, 3,
		4, 5, 6,
		7, 8, 9;
	m2 << 9, 8, 7,
		6, 5, 4,
		3, 2, 1;

	cout << "m1" << endl;
	cout << m1 << endl;
	cout << "m2" << endl;
	cout << m2 << endl;
	cout << "m1 + m2" << endl;
	cout << m1 + m2 << endl;
	cout << "m1 - m2" << endl;
	cout << m1 - m2 << endl;
	cout << "m1 * m2" << endl;
	cout << m1 * m2 << endl;
#pragma endregion

#pragma region 2
	cout << "task 2" << endl;
	Matrix4d m3 = Matrix4d::Random(4, 4);
	cout << "m3" << endl;
	cout << m3 << endl;
	cout << "transposition" << endl;
	cout << m3.transpose() << endl;
	cout << "conjugation" << endl;
	cout << m3.conjugate() << endl;
	cout << "trace" << endl;
	cout << m3.trace() << endl;
	cout << "inverse" << endl;
	cout << m3.inverse() << endl;
	cout << "determinant" << endl;
	cout << m3.determinant() << endl;
#pragma endregion

#pragma region 3
	cout << "task 3" << endl;
	Matrix<float, 5, 5> m4;
	m4 << 1.1f, 1.2f, 1.3f, 1.4f, 1.5f,
		2.1f, 2.2f, 2.3f, 2.4f, 2.5f,
		3.1f, 3.2f, 3.3f, 3.4f, 3.5f,
		4.1f, 4.2f, 4.3f, 4.4f, 4.5f,
		5.1f, 5.2f, 5.3f, 5.4f, 5.5f;

	cout << "m4" << endl;
	cout << m4 << endl;

	cout << "solver 1" << endl;
	EigenSolver<Matrix<float, 5, 5>> es(m4);
	Matrix<float, 5, 5> D = es.pseudoEigenvalueMatrix();
	Matrix<float, 5, 5> V = es.pseudoEigenvectors();
	cout << "The pseudo-eigenvalue matrix D is:" << endl << D << endl;
	cout << "The pseudo-eigenvector matrix V is:" << endl << V << endl;
	cout << "Finally, V * D * V^(-1) = " << endl << V * D * V.inverse() << endl;

	cout << "solver 2" << endl;
	SelfAdjointEigenSolver<Matrix<float, 5, 5>> es2(m4);
	cout << " The eigenvalues of m4 are:\n" << es2.eigenvalues() << endl;
	cout << " Here is a matrix whose columns are eigenvectors of m4\n"
		<< " corresponding to these eigenvalues:\n"
		<< es2.eigenvectors() << endl;
	cout << "m4*vec(1) = \n" << m4 * (es2.eigenvectors().col(0)) << endl;
	cout << "e(1)*vec(1) = \n" << es2.eigenvalues()(0) * es2.eigenvectors().col(0);

#pragma endregion

	return 0;
}