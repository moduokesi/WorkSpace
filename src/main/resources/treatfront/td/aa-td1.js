import * as THREE from 'three';
import { OrbitControls } from 'three/addons/OrbitControls.js';
// import { OrbitControls } from 'three/OrbitControls.js';

import {STLLoader} from 'three/addons/STLLoader.js'
// import {STLLoader} from 'three/STLLoader.js'



const width = 350
const height = 350
const scene = new THREE.Scene();
const camera = new THREE.PerspectiveCamera(30, 1, 1, 3000);
const renderer = new THREE.WebGLRenderer();
renderer.setSize(window.innerWidth, window.innerHeight);
document.body.appendChild(renderer.domElement);


//传入模型文件路径
window.loadModel1=function(filepath) {

	// 清空场景中的旧模型
	while (scene.children.length > 0) {
		scene.remove(scene.children[0]);
	}

	const loader = new STLLoader();
	const model = new THREE.Group();
	const modelSize = new THREE.Vector3();
	const scaleFactor = 0;
	const modelCenter = new THREE.Vector3();
	loader.load(filepath, function (ply) {
		ply.computeBoundingBox();
		const material = new THREE.MeshStandardMaterial({
			flatShading: true,
			color: 0xff0000,
			wireframe: false,
			side: THREE.DoubleSide,
			// color: 0x00ffff,
		});
		const mesh = new THREE.Mesh(ply, material);
		// 计算模型的边界框
		const boundingBox = new THREE.Box3().setFromObject(mesh);
		// 计算模型的中心位置
		modelCenter.addVectors(boundingBox.min, boundingBox.max).multiplyScalar(0.5);
		// 计算模型的尺寸
		// const modelSize = new THREE.Vector3();
		modelSize.subVectors(boundingBox.max, boundingBox.min);
		// 计算模型的缩放比例
		const scaleFactor = 30 / Math.max(modelSize.x, modelSize.y, modelSize.z);
		// 设置模型的位置和缩放
		mesh.position.copy(modelCenter.multiplyScalar(-scaleFactor));
		mesh.scale.set(scaleFactor, scaleFactor, scaleFactor);
		console.log('控制台查看ply对象结构', ply);
		console.log('场景3D模型数据', ply.scene);
		model.add(mesh);
	})


	scene.add(model); //模型对象添加到场景中


//辅助观察的坐标系
	const axesHelper = new THREE.AxesHelper(100);
	scene.add(axesHelper);


//光源设置
	const hemiLight = new THREE.HemisphereLight( 0xffffff, 0x000000, 1 );
	scene.add( hemiLight );

	const dirLight = new THREE.DirectionalLight( 0xffffff, 0.8 );
	dirLight.position.set( -200,-300,-200 );
	scene.add( dirLight );




//渲染器和相机

	camera.position.set(0,-60 , 0);
	camera.lookAt(0, 300, 0);
	renderer.setSize(width, height);
	renderer.antialias = true; // 开启抗锯齿
	document.getElementById('td1').appendChild(renderer.domElement);


// 渲染循环
	function render() {
		renderer.render(scene, camera);
		requestAnimationFrame(render);
	}

	render();

	const controls = new OrbitControls(camera, renderer.domElement);

}

