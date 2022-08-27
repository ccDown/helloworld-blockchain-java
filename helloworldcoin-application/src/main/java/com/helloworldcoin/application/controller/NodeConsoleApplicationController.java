package com.helloworldcoin.application.controller;

import com.helloworldcoin.application.vo.NodeConsoleApplicationApi;
import com.helloworldcoin.application.vo.block.DeleteBlocksRequest;
import com.helloworldcoin.application.vo.block.DeleteBlocksResponse;
import com.helloworldcoin.application.vo.framwork.Response;
import com.helloworldcoin.application.vo.miner.*;
import com.helloworldcoin.application.vo.node.*;
import com.helloworldcoin.application.vo.synchronizer.*;
import com.helloworldcoin.netcore.BlockchainNetCore;
import com.helloworldcoin.netcore.model.Node;
import com.helloworldcoin.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Node Console Application Controller : active miner、deactive miner and so on.
 *
 * @author x.king xdotking@gmail.com
 */
@RestController
public class NodeConsoleApplicationController {

    @Autowired
    private BlockchainNetCore blockchainNetCore;



    /**
     * is miner active
     */
    @RequestMapping(value = NodeConsoleApplicationApi.IS_MINER_ACTIVE,method={RequestMethod.GET,RequestMethod.POST})
    public Response<IsMinerActiveResponse> isMinerActive(@RequestBody IsMinerActiveRequest request){
        try {
            boolean isMineActive = blockchainNetCore.getBlockchainCore().getMiner().isActive();
            IsMinerActiveResponse response = new IsMinerActiveResponse();
            response.setMinerInActiveState(isMineActive);
            return Response.success(response);
        } catch (Exception e){
            String message = "'is miner active' error.";
            LogUtil.error(message,e);
            return Response.serviceUnavailable();
        }
    }
    /**
     * active miner
     */
    @RequestMapping(value = NodeConsoleApplicationApi.ACTIVE_MINER,method={RequestMethod.GET,RequestMethod.POST})
    public Response<ActiveMinerResponse> activeMiner(@RequestBody ActiveMinerRequest request){
        try {
            blockchainNetCore.getBlockchainCore().getMiner().active();
            ActiveMinerResponse response = new ActiveMinerResponse();
            return Response.success(response);
        } catch (Exception e){
            String message = "'active miner' error.";
            LogUtil.error(message,e);
            return Response.serviceUnavailable();
        }
    }
    /**
     * deactive miner
     */
    @RequestMapping(value = NodeConsoleApplicationApi.DEACTIVE_MINER,method={RequestMethod.GET,RequestMethod.POST})
    public Response<DeactiveMinerResponse> deactiveMiner(@RequestBody DeactiveMinerRequest request){
        try {
            blockchainNetCore.getBlockchainCore().getMiner().deactive();
            DeactiveMinerResponse response = new DeactiveMinerResponse();
            return Response.success(response);
        } catch (Exception e){
            String message = "'deactive miner' error.";
            LogUtil.error(message,e);
            return Response.serviceUnavailable();
        }
    }



    /**
     * is block searcher active
     */
    @RequestMapping(value = NodeConsoleApplicationApi.IS_BLOCK_SEARCHER_ACTIVE,method={RequestMethod.GET,RequestMethod.POST})
    public Response<IsBlockSearcherActiveResponse> isBlockSearcherActive(@RequestBody IsBlockSearcherActiveRequest request){
        try {
            boolean isBlockSearcherActive = blockchainNetCore.getNetCoreConfiguration().isBlockSearcherActive();
            IsBlockSearcherActiveResponse response = new IsBlockSearcherActiveResponse();
            response.setBlockSearcherInActiveState(isBlockSearcherActive);
            return Response.success(response);
        } catch (Exception e){
            String message = "'is block searcher active' error.";
            LogUtil.error(message,e);
            return Response.serviceUnavailable();
        }
    }
    /**
     * active block searcher
     */
    @RequestMapping(value = NodeConsoleApplicationApi.ACTIVE_BLOCK_SEARCHER,method={RequestMethod.GET,RequestMethod.POST})
    public Response<ActiveBlockSearcherResponse> activeBlockSearcher(@RequestBody ActiveBlockSearcherRequest request){
        try {
            blockchainNetCore.getNetCoreConfiguration().activeBlockSearcher();
            ActiveBlockSearcherResponse response = new ActiveBlockSearcherResponse();
            return Response.success(response);
        } catch (Exception e){
            String message = "'active block searcher' error.";
            LogUtil.error(message,e);
            return Response.serviceUnavailable();
        }
    }
    /**
     * deactive block searcher
     */
    @RequestMapping(value = NodeConsoleApplicationApi.DEACTIVE_BLOCK_SEARCHER,method={RequestMethod.GET,RequestMethod.POST})
    public Response<DeactiveBlockSearcherResponse> deactiveBlockSearcher(@RequestBody DeactiveBlockSearcherRequest request){
        try {
            blockchainNetCore.getNetCoreConfiguration().deactiveBlockSearcher();
            DeactiveBlockSearcherResponse response = new DeactiveBlockSearcherResponse();
            return Response.success(response);
        } catch (Exception e){
            String message = "'deactive block searcher' error.";
            LogUtil.error(message,e);
            return Response.serviceUnavailable();
        }
    }



    /**
     * add node
     */
    @RequestMapping(value = NodeConsoleApplicationApi.ADD_NODE,method={RequestMethod.GET,RequestMethod.POST})
    public Response<AddNodeResponse> addNode(@RequestBody AddNodeRequest request){
        try {
            String ip = request.getIp();
            if(blockchainNetCore.getNodeService().queryNode(ip) != null){
                AddNodeResponse response = new AddNodeResponse();
                response.setAddNodeSuccess(true);
                return Response.success(response);
            }
            Node node = new Node();
            node.setIp(ip);
            node.setBlockchainHeight(0);
            blockchainNetCore.getNodeService().addNode(node);
            AddNodeResponse response = new AddNodeResponse();
            response.setAddNodeSuccess(true);
            return Response.success(response);
        } catch (Exception e){
            String message = "'add node' error.";
            LogUtil.error(message,e);
            return Response.serviceUnavailable();
        }
    }
    /**
     * update node
     */
    @RequestMapping(value = NodeConsoleApplicationApi.UPDATE_NODE,method={RequestMethod.GET,RequestMethod.POST})
    public Response<UpdateNodeResponse> updateNode(@RequestBody UpdateNodeRequest request){
        try {
            Node node = new Node();
            node.setIp(request.getIp());
            node.setBlockchainHeight(request.getBlockchainHeight());
            blockchainNetCore.getNodeService().updateNode(node);
            UpdateNodeResponse response = new UpdateNodeResponse();
            return Response.success(response);
        } catch (Exception e){
            String message = "'update node' error.";
            LogUtil.error(message,e);
            return Response.serviceUnavailable();
        }
    }
    /**
     * delete node
     */
    @RequestMapping(value = NodeConsoleApplicationApi.DELETE_NODE,method={RequestMethod.GET,RequestMethod.POST})
    public Response<DeleteNodeResponse> deleteNode(@RequestBody DeleteNodeRequest request){
        try {
            blockchainNetCore.getNodeService().deleteNode(request.getIp());
            DeleteNodeResponse response = new DeleteNodeResponse();
            return Response.success(response);
        } catch (Exception e){
            String message = "'delete node' error.";
            LogUtil.error(message,e);
            return Response.serviceUnavailable();
        }
    }
    /**
     * query all nodes
     */
    @RequestMapping(value = NodeConsoleApplicationApi.QUERY_ALL_NODES,method={RequestMethod.GET,RequestMethod.POST})
    public Response<QueryAllNodesResponse> queryAllNodes(@RequestBody QueryAllNodesRequest request){
        try {
            List<Node> nodes = blockchainNetCore.getNodeService().queryAllNodes();

            List<NodeVo> nodeVos = new ArrayList<>();
            if(nodes != null){
                for (Node node:nodes) {
                    NodeVo nodeVo = new NodeVo();
                    nodeVo.setIp(node.getIp());
                    nodeVo.setBlockchainHeight(node.getBlockchainHeight());
                    nodeVos.add(nodeVo);
                }
            }

            QueryAllNodesResponse response = new QueryAllNodesResponse();
            response.setNodes(nodeVos);
            return Response.success(response);
        } catch (Exception e){
            String message = "'query all nodes' error.";
            LogUtil.error(message,e);
            return Response.serviceUnavailable();
        }
    }



    /**
     * is node searcher active
     */
    @RequestMapping(value = NodeConsoleApplicationApi.IS_NODE_SEARCHER_ACTIVE,method={RequestMethod.GET,RequestMethod.POST})
    public Response<IsNodeSearcherActiveResponse> isNodeSearcherActive(@RequestBody IsNodeSearcherActiveRequest request){
        try {
            boolean isNodeSearcherActive = blockchainNetCore.getNetCoreConfiguration().isNodeSearcherActive();
            IsNodeSearcherActiveResponse response = new IsNodeSearcherActiveResponse();
            response.setNodeSearcherInActiveState(isNodeSearcherActive);
            return Response.success(response);
        } catch (Exception e){
            String message = "'is node searcher active' error.";
            LogUtil.error(message,e);
            return Response.serviceUnavailable();
        }
    }
    /**
     * active node searcher
     */
    @RequestMapping(value = NodeConsoleApplicationApi.ACTIVE_NODE_SEARCHER,method={RequestMethod.GET,RequestMethod.POST})
    public Response<ActiveNodeSearcherResponse> activeNodeSearcher(@RequestBody ActiveNodeSearcherRequest request){
        try {
            blockchainNetCore.getNetCoreConfiguration().activeNodeSearcher();
            ActiveNodeSearcherResponse response = new ActiveNodeSearcherResponse();
            return Response.success(response);
        } catch (Exception e){
            String message = "'active node searcher' error.";
            LogUtil.error(message,e);
            return Response.serviceUnavailable();
        }
    }
    /**
     * deactive node searcher
     */
    @RequestMapping(value = NodeConsoleApplicationApi.DEACTIVE_NODE_SEARCHER,method={RequestMethod.GET,RequestMethod.POST})
    public Response<DeactiveNodeSearcherResponse> deactiveNodeSearcher(@RequestBody DeactiveNodeSearcherRequest request){
        try {
            blockchainNetCore.getNetCoreConfiguration().deactiveNodeSearcher();
            DeactiveNodeSearcherResponse response = new DeactiveNodeSearcherResponse();
            return Response.success(response);
        } catch (Exception e){
            String message = "'deactive node searcher' error.";
            LogUtil.error(message,e);
            return Response.serviceUnavailable();
        }
    }



    /**
     * delete blocks
     */
    @RequestMapping(value = NodeConsoleApplicationApi.DELETE_BLOCKS,method={RequestMethod.GET,RequestMethod.POST})
    public Response<DeleteBlocksResponse> deleteBlocks(@RequestBody DeleteBlocksRequest request){
        try {
            blockchainNetCore.getBlockchainCore().deleteBlocks(request.getBlockHeight());
            DeleteBlocksResponse response = new DeleteBlocksResponse();
            return Response.success(response);
        } catch (Exception e){
            String message = "'delete blocks' error.";
            LogUtil.error(message,e);
            return Response.serviceUnavailable();
        }
    }



    /**
     * Get Miner Mine Max Block Height
     */
    @RequestMapping(value = NodeConsoleApplicationApi.GET_MINER_MINE_MAX_BLOCK_HEIGHT,method={RequestMethod.GET,RequestMethod.POST})
    public Response<GetMinerMineMaxBlockHeightResponse> getMinerMineMaxBlockHeight(@RequestBody GetMinerMineMaxBlockHeightRequest request){
        try {
            long  maxBlockHeight = blockchainNetCore.getBlockchainCore().getMiner().getMinerMineMaxBlockHeight();
            GetMinerMineMaxBlockHeightResponse response = new GetMinerMineMaxBlockHeightResponse();
            response.setMaxBlockHeight(maxBlockHeight);
            return Response.success(response);
        } catch (Exception e){
            String message = "'Get Miner Mine Max Block Height' error.";
            LogUtil.error(message,e);
            return Response.serviceUnavailable();
        }
    }
    /**
     * Set Miner Mine Max Block Height
     */
    @RequestMapping(value = NodeConsoleApplicationApi.SET_MINER_MINE_MAX_BLOCK_HEIGHT,method={RequestMethod.GET,RequestMethod.POST})
    public Response<SetMinerMineMaxBlockHeightResponse> setMinerMineMaxBlockHeight(@RequestBody SetMinerMineMaxBlockHeightRequest request){
        try {
            long height = request.getMaxBlockHeight();
            blockchainNetCore.getBlockchainCore().getMiner().setMinerMineMaxBlockHeight(height);
            SetMinerMineMaxBlockHeightResponse response = new SetMinerMineMaxBlockHeightResponse();
            return Response.success(response);
        } catch (Exception e){
            String message = "'Set Miner Mine Max Block Height' error.";
            LogUtil.error(message,e);
            return Response.serviceUnavailable();
        }
    }
}